import React from 'react';
import { changeCSS, _$ } from '../common/utils';
import { MODAL_TOP } from '../common/variable';
import './modal.scss';

function Modal() {
  function goNextProblem() {
    const $modal = _$('.modalBackground');
    changeCSS($modal, 'top', MODAL_TOP.HIDE);
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
