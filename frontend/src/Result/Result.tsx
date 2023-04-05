import React, { useEffect, useState } from 'react';
import { isDevMode } from '../common/utils';
import {
  FETCH_METHOD,
  FETCH_URL,
  RELEASE_URL,
  LAST_PROBLEM,
} from '../common/variable';
import './result.scss';

function Result() {
  const [answerTable, setAnswerTable] = useState<string[]>([]);
  const [resultTitle, setResultTitle] = useState('결과를 가져오고 있습니다.');
  const problemNumbers: string[] = Array.from(
    { length: LAST_PROBLEM },
    (_, index) => `${index + 1}번`
  );

  useEffect(() => {
    async function getResultFromServer(): Promise<void> {
      const URL = isDevMode() ? `${FETCH_URL}/result` : `${RELEASE_URL}/result`;

      await fetch(URL, {
        method: FETCH_METHOD.GET,
        headers: {
          'Content-type': 'application/json',
        },
      })
        .then((res) => {
          return res.json();
        })
        .then(({ resultTitle, results }) => {
          setResultTitle(resultTitle);
          setAnswerTable([...results]);
        });
    }

    getResultFromServer();
  }, []);

  return (
    <section className="resultContainer">
      <h1 className="resultContainer__title">{resultTitle}</h1>
      <section className="resultBox">
        <ul>
          {answerTable.map((answer, index) => (
            <li key={`result${index}`}>
              <h2>{problemNumbers[index]}</h2>
              <h3>{answer ? 'O' : 'X'}</h3>
            </li>
          ))}
        </ul>
      </section>
    </section>
  );
}

export default Result;
