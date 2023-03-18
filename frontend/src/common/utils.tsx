const _$ = (cssSelector: string) =>
  document.querySelector(cssSelector) as HTMLElement;

const changeCSS = (target: HTMLElement | null, key: any, newValue: string) => {
  if (target) {
    target.style[key] = newValue;
  }
};

export { _$, changeCSS };
