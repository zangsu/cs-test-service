import React from 'react';
import './result.scss';

function Result() {
  const problemNumbers: string[] = ['1번', '2번', '3번', '4번', '5번'];
  const userAnswerCorrects: boolean[] = [true, true, true, false, true];

  return (
    <section className="resultContainer">
      <h1 className="resultContainer__title">결과 제목</h1>
      <section className="resultBox">
        <ul>
          {problemNumbers.map((problemNumber, index) => (
            <li key={`result${index}`}>
              <h2>{problemNumber}</h2>
              <h3>{userAnswerCorrects[index] ? 'O' : 'X'}</h3>
            </li>
          ))}
        </ul>
      </section>
    </section>
  );
}

export default Result;
