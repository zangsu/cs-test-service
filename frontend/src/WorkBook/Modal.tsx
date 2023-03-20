import React, { useContext } from 'react';
import { changeCSS, _$ } from '../common/utils';
import { LAST_PROBLEM, MODAL_TOP } from '../common/variable';
import './modal.scss';
import { contextValue, contextType } from './WorkBook';
import { useNavigate } from 'react-router-dom';

function Modal() {
  const navigate = useNavigate();
  const workBookContext = useContext<contextValue>(contextType);
  const getProblems = workBookContext.handleModalClick;
  const setProblemNumber = workBookContext.setProblemNumber;
  const problemNumber = workBookContext.problemNumber;

  function goNextProblem() {
    if (problemNumber == LAST_PROBLEM) {
      navigate('/');
      return;
    }

    const $modal = _$('.modalBackground');
    changeCSS($modal, 'top', MODAL_TOP.HIDE);
    getProblems(problemNumber + 1);
    setProblemNumber((problemNumber) => problemNumber + 1);
  }

  return (
    <section className="modalBackground">
      <section className="modalContainer">
        <div className="modalContainer__title">정답!</div>
        <h1 className="modalContainer__description">
          정답은 <span className="modalContainer__correctAnswer">3번</span>{' '}
          입니다
        </h1>
        <button className="modalContainer__nextBtn" onClick={goNextProblem}>
          다음 문제
        </button>
      </section>
    </section>
  );
}

export default Modal;
