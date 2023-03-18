import React, { useEffect } from 'react';
import { _$ } from '../common/utils';
import './workBook.scss';

function WorkBook() {
  useEffect(showProblem, []);

  const problemNumber = 1;
  const problemNumberMapper: string[] = ['', '①', '②', '③', '④', '⑤'];
  let problemTitle = '';
  let problemList: string[] = [];

  function clearChoiceNodeClass(): void {
    const $choiceNodes = Array.from(document.querySelectorAll('li'));
    $choiceNodes.forEach(($choiceNode) => ($choiceNode.className = ''));
  }

  function eventToChoiceNode($targetNode: HTMLElement): void {
    $targetNode.addEventListener('click', () => {
      clearChoiceNodeClass();
      $targetNode.classList.toggle('clicked');
    });
  }

  function makeChoiceNode(
    choiceNumber: string,
    choiceDescription: string
  ): HTMLElement {
    const $choiceNode = document.createElement('li');
    const $choiceNumber = document.createElement('h3');
    const $choiceDescription = document.createElement('h4');

    $choiceNumber.innerHTML = choiceNumber;
    $choiceDescription.innerHTML = choiceDescription;

    $choiceNode.appendChild($choiceNumber);
    $choiceNode.appendChild($choiceDescription);

    eventToChoiceNode($choiceNode);

    return $choiceNode;
  }

  // 통신 들어가면 비동기 처리 해주기
  function getProblems(): void {
    problemList = ['1번 보기', '2번 보기', '3번 보기', '4번 보기', '5번 보기'];
    problemTitle = '문제 설명';
  }

  function showProblem(): void {
    getProblems();
    const $problemBox = _$('ul');

    // Strict Mode로 2번 호출되는 것 방지
    if ($problemBox.children.length) {
      return;
    }

    problemList.forEach((problem, index) => {
      const numberToString: string = problemNumberMapper[index + 1];
      $problemBox.appendChild(makeChoiceNode(numberToString, problem));
    });
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
          <ul></ul>
        </div>
      </section>
      <button className="problemSubmitBtn">Submit</button>
    </section>
  );
}

export default WorkBook;
