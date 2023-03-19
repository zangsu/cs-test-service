import React, { useEffect, useState } from 'react';
import { FETCH_URL, FETCH_METHOD, MODAL_TOP } from '../common/variable';
import { changeCSS, _$ } from '../common/utils';
import './workBook.scss';
import Modal from './Modal';

function WorkBook() {
  useEffect(() => getProblems, []);

  const [problemList, setProblemList] = useState<string[]>([]);
  const [clickedNode, setClickedNode] = useState<HTMLElement>();
  const [problemTitle, setProblemTitle] = useState<string>('');
  const [problemNumber, setProblemNumber] = useState(1);

  const problemNumberMapper: string[] = ['', '①', '②', '③', '④', '⑤'];

  function clickChoiceNode(event: React.MouseEvent<HTMLLIElement>) {
    let $targetNode = event.target as HTMLElement;

    $targetNode =
      $targetNode.tagName === 'LI'
        ? $targetNode
        : ($targetNode.parentElement as HTMLElement);

    if ($targetNode === clickedNode) {
      return;
    }

    $targetNode.classList.add('clicked');

    if (clickedNode) {
      clickedNode.classList.remove('clicked');
    }

    setClickedNode($targetNode);
  }

  // 통신 들어가면 비동기 처리 해주기
  function getProblems(): void {
    // fetch(`${FETCH_URL}/problem?problemNumber${problemNumber}`, {
    //   method: FETCH_METHOD.GET,
    // });

    setProblemList(() => [
      '1번 보기',
      '2번 보기',
      '3번 보기',
      '4번 보기',
      '5번 보기',
    ]);

    setProblemTitle('문제 설명');
  }

  function showModal(userInput: string, answer: string): void {
    const $modalTitle = _$('.modalContainer__title');
    const $modalDescription = _$('.modalContainer__description');

    if (userInput === answer) {
      $modalTitle.innerHTML = '정답 !';
      $modalDescription.innerHTML = `정답은 ${answer}번 입니다!`;
    } else {
      $modalTitle.innerHTML = '오답 !';
      $modalDescription.innerHTML = `정답은 <span style="color:red;">${answer}번</span> 입니다`;
    }

    const $modal = _$('.modalBackground');
    changeCSS($modal, 'top', MODAL_TOP.SHOW);
  }

  function submitAnswer(): void {
    let userInput = '0';
    const $choiceNodes = document.querySelectorAll('li');

    for (let i = 0; i < $choiceNodes.length; i++) {
      if ($choiceNodes[i].classList.contains('clicked')) {
        userInput = String(i + 1);
        break;
      }
    }

    if (userInput === '0') {
      alert('정답을 선택해주세요.');
      return;
    }

    // 통신 함수 추후 추가
    const problemAnswer = '3';
    showModal(userInput, problemAnswer);
  }

  return (
    <section className="container">
      <Modal></Modal>
      <section className="problembox">
        <div className="problembox__problem">
          <h1 className="problembox__problemNumber">
            CS Test : {problemNumber}번 문제
          </h1>
          <h2 className="problembox__problemDescription">{problemTitle}</h2>
        </div>
        <div className="problembox__choicebox">
          <ul>
            {problemList.map((problem, index) => (
              <li
                className="problembox__choice"
                key={index}
                onClick={clickChoiceNode}
              >
                <h3>{problemNumberMapper[index + 1]}</h3>
                <h4>{problem}</h4>
              </li>
            ))}
          </ul>
        </div>
      </section>
      <button className="problemSubmitBtn" onClick={submitAnswer}>
        Submit
      </button>
    </section>
  );
}

export default WorkBook;
