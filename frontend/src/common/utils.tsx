const _$ = (cssSelector: string) =>
  document.querySelector(cssSelector) as HTMLElement;

const changeCSS = (target: HTMLElement | null, key: any, newValue: string) => {
  if (target) {
    target.style[key] = newValue;
  }
};

const parseSchoolID = (IDString: string) => {
  const IDNumber = parseInt(IDString);
  return `20${IDNumber}`;
};

const isDevMode = () => {
  const LOCAL_HOST = 'localhost';
  console.log(window.location.hostname === LOCAL_HOST);
  return window.location.hostname === LOCAL_HOST;
};

export { _$, changeCSS, parseSchoolID, isDevMode };
