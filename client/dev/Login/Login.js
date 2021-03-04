import React, {useEffect, useState} from 'react';
import {Card} from 'primereact/card';
import {InputText} from 'primereact/inputtext';
import {Button} from "primereact/button";
import {Password} from 'primereact/password';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Skeleton } from 'primereact/skeleton';
import axios from "axios";

import 'primereact/resources/themes/fluent-light/theme.css';
import 'primeflex/primeflex.css';
import 'primeicons/primeicons.css';
import 'primereact/resources/primereact.css';

import './style.scss';

const Login = () => {
  const [login, setLogin] = useState(true);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [currency, setCurrency] = useState(null);

  useEffect(() => {
    getExchangeRate().then(response => {
      return setCurrency(response);
    });
  }, []);

  const bodyTemplate = () => {
    return <Skeleton></Skeleton>
  };

  async function getExchangeRate() {
    const response = await axios('https://api.monobank.ua/bank/currency');
    return [
      {
        'currency': '$',
        'buy': response.data[0].rateBuy,
        'sell': response.data[0].rateSell
      },
      {
        'currency': '€',
        'buy': response.data[1].rateBuy,
        'sell': response.data[1].rateSell
      }
    ];
  }

  async function submitForm(e) {
    e.preventDefault();
    const user = {
      action: login ? 'login' : 'sign up',
      email,
      password
    };
    const options = {
      method: 'post',
      headers: new Headers({
        Accept: 'application/json',
        "Content-Type": 'application/json',
      }),
      body: JSON.stringify(user),
    };
    fetch('LINKtOsLAVIK', options);
    const response = await axios('responseLink');
    if (response.status === 200) {
      //cool!
    } else {
      setEmail('');
      setPassword('');
      alert(login ? 'email or password is incorrect' : 'this email is already used');
    }
  }

  const signUpClick = () => {
    setLogin(!login);
    if (!login) {
      setEmail('');
      setPassword('');
    }
  };

  return (
    <div className="wrapper">
      <Card className="card" title={login ? "Login" : "Sign up"}>
        <div className="p-fluid wrapper">
          <div className="p-field">
            <InputText className="p-d-block p-mb-2 input" placeholder="email" value={email} onChange={(e) => setEmail(e.target.value)} type="email"/>
          </div>
          <div className="p-field">
            <Password className="input" placeholder="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          </div>
          <div className="p-field">
            <Button className="p-button-raised button" onClick={submitForm} label={login ? "Login" : "Sign up"}/>
          </div>
          <Button className="p-button-outlined p-button-secondary p-button-sm" onClick={signUpClick} label={login ? "Sign up" : 'back'}/>
        </div>
      </Card>
      <div className="table">
        {
          currency ? (
            <DataTable value={currency}>
              <Column field="currency" header="Currency" />
              <Column field="buy" header="Buy" />
              <Column field="sell" header="Sell" />
            </DataTable>
          ) : (
            <DataTable value={[{}, {}]} className="p-datatable-striped">
              <Column header="Currency" body={bodyTemplate} />
              <Column header="Buy" body={bodyTemplate} />
              <Column header="Sell" body={bodyTemplate} />
            </DataTable>
          )
        }
      </div>
    </div>
  )
};

export default Login;