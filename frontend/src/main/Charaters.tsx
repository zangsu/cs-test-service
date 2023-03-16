import React from 'react';

function Characters() {
  const characterBlue = require('../assets/img/blue.png');
  const characterGreen = require('../assets/img/green.png');
  const characterRed = require('../assets/img/red.png');
  const characterSky = require('../assets/img/sky.png');
  const characterYellow = require('../assets/img/yellow.png');

  return (
    <section>
      <img
          src={characterBlue}
          alt="characterBlue"
          className="bodycontainer__character bodycontainer__character--blue"
        />
        <img
          src={characterGreen}
          alt="characterGreen"
          className="bodycontainer__character bodycontainer__character--green"
        />
        <img
          src={characterRed}
          alt="characterRed"
          className="bodycontainer__character bodycontainer__character--red"
        />
        <img
          src={characterSky}
          alt="characterSky"
          className="bodycontainer__character bodycontainer__character--sky"
        />
        <img
          src={characterYellow}
          alt="characterYellow"
          className="bodycontainer__character bodycontainer__character--yellow"
        />
    </section>
  )
}

export default Characters;