import React, { useEffect, useState } from 'react';
import { _$ } from '../common/utils';
import './workBook.scss';

function WorkBook() {
  useEffect(() => getProblems, []);

  const [problemList, setProblemList] = useState<string[]>([]);
  const [clickedNode, setClickedNode] = useState<HTMLElement>();
  const [problemTitle, setProblemTitle] = useState<string>('');

  const problemNumber = 1;
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
    setProblemList(() => [
      ...problemList,
      '1번 보기',
      '2번 보기',
      '3번 보기',
      '4번 보기',
      '5번 보기',
    ]);

    setProblemTitle('문제 설명');
  }

  return (
    <section className="container">
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
      <button className="problemSubmitBtn">Submit</button>
    </section>
  );
}

export default WorkBook;
