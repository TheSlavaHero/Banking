import React, {useEffect, useState} from 'react';
import ReactDOM from 'react-dom';
import PrimeReact from 'primereact/api';

PrimeReact.ripple = true;

import Login from '../Login/Login';

const Main = () => {
  return (
    <>
      <Login />
    </>
  )
};

ReactDOM.render(
  <Main />,
  document.getElementById('app')
);