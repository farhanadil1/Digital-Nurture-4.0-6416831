import React from 'react';

function App() {
  const flag = false;

  const players = [
    { name: "Jack", score: 50 },
    { name: "Michael", score: 70 },
    { name: "John", score: 40 },
    { name: "Ann", score: 61 },
    { name: "Elisabeth", score: 61 },
    { name: "Sachin", score: 95 },
    { name: "Dhoni", score: 100 },
    { name: "Virat", score: 84 },
    { name: "Jadeja", score: 64 },
    { name: "Raina", score: 75 },
    { name: "Rohit", score: 80 }
  ];

  const IndianTeam = ["Sachin1", "Dhoni2", "Virat3", "Rohit4", "Yuvraj5", "Raina6"];
  const T20Players = ['First Player', 'Second Player', 'Third Player'];
  const RanjiTrophyPlayers = ['Fourth Player', 'Fifth Player', 'Sixth Player'];
  const IndianPlayers = [...T20Players, ...RanjiTrophyPlayers];

  const ScoreBelow70 = players.filter(p => p.score < 70);
  const [first, , third, , fifth] = IndianTeam;
  const [, second, , fourth, , sixth] = IndianTeam;

  if (flag) {
    return (
      <div>
        <h1>List of Players</h1>
        <ul>
          {players.map((p, i) => (
            <li key={i}>Mr. {p.name} <span>{p.score}</span></li>
          ))}
        </ul>
        <hr />
        <h1>List of Players having Scores Less than 70</h1>
        <ul>
          {ScoreBelow70.map((p, i) => (
            <li key={i}>Mr. {p.name} <span>{p.score}</span></li>
          ))}
        </ul>
      </div>
    );
  } else {
    return (
      <div>
        <h2>Odd Players</h2>
        <ul>
          <li>First : {first}</li>
          <li>Third : {third}</li>
          <li>Fifth : {fifth}</li>
        </ul>
        <hr />
        <h2>Even Players</h2>
        <ul>
          <li>Second : {second}</li>
          <li>Fourth : {fourth}</li>
          <li>Sixth : {sixth}</li>
        </ul>
        <hr />
        <h2>List of Indian Players Merged:</h2>
        <ul>
          {IndianPlayers.map((p, i) => (
            <li key={i}>Mr. {p}</li>
          ))}
        </ul>
      </div>
    );
  }
}

export default App;
