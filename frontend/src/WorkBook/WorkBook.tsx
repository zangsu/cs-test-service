import React, { useEffect, useState, createContext } from 'react';
import {
  FETCH_URL,
  FETCH_METHOD,
  MODAL_TOP,
  LAST_PROBLEM,
  USER_NONE_INPUT,
} from '../common/variable';
import { changeCSS, _$ } from '../common/utils';
import './workBook.scss';
import Modal from './Modal';

type contextValue = {
  handleModalClick: (problemNumber: number) => void;
  setProblemNumber: (callBack: (problemNumber: number) => number) => void;
  problemNumber: number;
};

/* eslint-disable @typescript-eslint/no-empty-function */
const contextType = createContext<contextValue>({
  handleModalClick: () => {},
  setProblemNumber: () => {},
  problemNumber: 1,
});
/* eslint-enable @typescript-eslint/no-empty-function */

function WorkBook() {
  const setProblemData = async () => await getProblems(1);
  let isFirstCall = true;

  useEffect(() => {
    if (isFirstCall) {
      setProblemData();
      isFirstCall = false;
    }
  }, []);

  const [problemList, setProblemList] = useState<string[]>([]);
  const [clickedNode, setClickedNode] = useState<HTMLElement>();
  const [problemTitle, setProblemTitle] = useState<string>('');
  const [problemNumber, setProblemNumber] = useState(1);
  const problemNumberMapper: string[] = ['', '①', '②', '③', '④', '⑤'];

  const workBookContext: contextValue = {
    handleModalClick: getProblems,
    setProblemNumber,
    problemNumber,
  };

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
  async function getProblems(number: number): Promise<void> {
    await fetch(`${FETCH_URL}/problem?problemNumber=${number}`, {
      headers: {
        'Content-type': 'application/json',
      },
      method: FETCH_METHOD.GET,
    })
      .then((res) => res.json())
      .then(({ example, problem }) => {
        setProblemList(() => [...example]);
        setProblemTitle(problem);
      });
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

    if (problemNumber == LAST_PROBLEM) {
      const $nextBtn = _$('.modalContainer__nextBtn');
      $nextBtn.innerHTML = '결과 확인';
    }

    const $modal = _$('.modalBackground');
    changeCSS($modal, 'top', MODAL_TOP.SHOW);
  }

  async function submitAnswer(): Promise<void> {
    let userInput = USER_NONE_INPUT;
    const $choiceNodes = document.querySelectorAll('li');

    for (let i = 0; i < $choiceNodes.length; i++) {
      if ($choiceNodes[i].classList.contains('clicked')) {
        userInput = String(i + 1);
        break;
      }
    }

    if (userInput === USER_NONE_INPUT) {
      alert('정답을 선택해주세요.');
      return;
    }

    // 통신 함수 추후 추가
    let problemAnswer = '';

    await fetch(`${FETCH_URL}/problem?problemNumber=${problemNumber}`, {
      method: FETCH_METHOD.POST,
      headers: {
        'Content-type': 'application/json',
      },
      body: JSON.stringify({
        userAnswer: userInput,
      }),
    })
      .then((res) => res.json())
      .then(({ answer }) => {
        problemAnswer = answer;
      });

    showModal(userInput, problemAnswer);
  }

  return (
    <section className="container">
      <contextType.Provider value={workBookContext}>
        <Modal></Modal>
      </contextType.Provider>
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
export { contextValue, contextType };
