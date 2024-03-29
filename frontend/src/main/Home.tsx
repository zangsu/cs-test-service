import './reset.scss';
import './home.scss';
import React, { useEffect } from 'react';
import Logo from './Logo';
import Characters from './Charaters';
import { changeCSS, isDevMode, parseSchoolID, _$ } from '../common/utils';
import { useNavigate } from 'react-router-dom';
import handImg from '../assets/img/longhand.png';
import { FETCH_METHOD, FETCH_URL, RELEASE_URL } from '../common/variable';

function Home() {
  const navigate = useNavigate();
  useEffect(makeInputValues, []);

  function nameValidationTest(): boolean {
    const $userNameInput = _$('#userNameInput') as HTMLInputElement;

    if ($userNameInput.value) {
      return true;
    }

    return false;
  }

  function gradeValidationTest(): boolean {
    const $gradeInput = _$('#schoolIDInput') as HTMLInputElement;

    if ($gradeInput.value) {
      return true;
    }

    return false;
  }

  async function handImgClickBtn(): Promise<void> {
    if (!gradeValidationTest() || !nameValidationTest()) {
      alert('입력 칸을 모두 채워주세요');
      return;
    }

    const $handImg = _$('#bodycontainer__handImg');
    const $startBtn = _$('.bodycontainer__startbtn');

    changeCSS($handImg, 'transform', 'rotateY(0deg) rotate(-20deg)');

    setTimeout(() => {
      changeCSS(
        $handImg,
        'transform',
        'rotateY(0deg) rotate(-20deg) scale(110%)'
      );
      changeCSS($startBtn, 'transform', 'scale(110%)');
      changeCSS($startBtn, 'backgroundColor', '#2F83FF');
      changeCSS($startBtn, 'color', '#F1F0EB');
    }, 1000);

    setTimeout(() => changeCSS($handImg, 'opacity', '0'), 2000);
    setTimeout(() => navigate('/workBook'), 2500);
  }

  async function submitUserInfo(): Promise<void> {
    const $userNameInput = _$('#userNameInput') as HTMLInputElement;
    const $userIDInput = _$('#schoolIDInput') as HTMLInputElement;

    const userName = $userNameInput.value;
    const userID = parseSchoolID($userIDInput.value);

    const URL = isDevMode()
      ? `${FETCH_URL}/user?userName=${userName}&userID=${userID}`
      : `${RELEASE_URL}/user?userName=${userName}&userID=${userID}`;

    await fetch(URL, {
      method: FETCH_METHOD.POST,
    });
  }

  async function clickStartBtn() {
    await submitUserInfo();
    handImgClickBtn();
  }

  function clearInputValueCSS() {
    const $inputList = _$('.bodycontainer__inputvalues');
    const $inputValues: NodeList = $inputList.childNodes;

    for (let index = 0; index < $inputValues.length; index++) {
      const $inputValue = $inputValues[index] as HTMLElement;
      $inputValue.classList.remove('clicked');
    }
  }

  function toggleInputValues(): void {
    const INPUT_VALUES_SHOW = '50%';
    const INPUT_VALUES_HIDDEN = '3%';
    const $inputValues: HTMLElement = _$('.bodycontainer__inputvalues');

    $inputValues.style.bottom === INPUT_VALUES_HIDDEN
      ? changeCSS($inputValues, 'bottom', INPUT_VALUES_SHOW)
      : changeCSS($inputValues, 'bottom', INPUT_VALUES_HIDDEN);
  }

  function fillDegreeInput(inputText: string): void {
    clearInputValueCSS();
    const $degreeInput = _$('#schoolIDInput') as HTMLInputElement;
    $degreeInput.value = inputText;
  }

  function makeInputValues(): void {
    const degrees = [17, 18, 19, 20, 21, 22, 23];
    const $inputList = _$('.bodycontainer__inputvalues');

    if (!$inputList.children.length) {
      degrees.forEach((degree) => {
        const $inputValue: HTMLElement = document.createElement('li');
        $inputValue.textContent = `${degree}학번`;

        $inputValue.addEventListener('click', () => {
          fillDegreeInput(`${degree}학번`);
          toggleInputValues();
          $inputValue.classList.add('clicked');
        });

        $inputList.appendChild($inputValue);
      });
    }
  }

  return (
    <section className="mainframe">
      <header className="headercontainer">
        <h3 className="headercontainer__logoarea">
          <Logo></Logo>
        </h3>
      </header>
      <section className="bodycontainer">
        <div className="bodycontainer__title">
          <h2 className="bodycontainer__fadedtitle bodycontainer__fadedtitle--left">
            C
          </h2>
          <h2 className="bodycontainer__fadedtitle bodycontainer__fadedtitle--right">
            S
          </h2>
          <h1>Come on Start</h1>
        </div>
        <Characters></Characters>
        <div className="bodycontainer__inputbox">
          <ul className="bodycontainer__inputvalues"></ul>
          <div className="bodycontainer__inputs">
            <label htmlFor="userNameInput">
              <span>이름</span>
              <input type="text" id="userNameInput" placeholder="type!" />
            </label>
            <label
              htmlFor="schoolIDInput"
              id="label--schoolID"
              onClick={toggleInputValues}
            >
              <span>학번</span>
              <input
                type="text"
                id="schoolIDInput"
                placeholder="click!"
                disabled
              />
            </label>
          </div>
          <button className="bodycontainer__startbtn" onClick={clickStartBtn}>
            Let`s Go
          </button>
        </div>
        <img src={handImg} alt="손 이미지" id="bodycontainer__handImg" />
      </section>
    </section>
  );
}

export default Home;
