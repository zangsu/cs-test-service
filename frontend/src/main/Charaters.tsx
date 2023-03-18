import React from 'react';
import characterBlue from '../assets/img/blue.png';
import characterGreen from '../assets/img/green.png';
import characterRed from '../assets/img/red.png';
import characterSky from '../assets/img/sky.png';
import characterYellow from '../assets/img/yellow.png';

function Characters() {
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
  );
}

export default Characters;
