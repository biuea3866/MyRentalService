import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import createSagaMiddleware from 'redux-saga';
import rootReducer, { rootSaga } from './modules';
import { BrowserRouter } from 'react-router-dom';
import { saveUser, check } from './modules/user';

const sagaMiddleware = createSagaMiddleware();
const store = createStore(
  rootReducer,
  composeWithDevTools(applyMiddleware(sagaMiddleware))
);

function loadUser() {
  try {
    const user = JSON.parse(localStorage.getItem('user'));
  
    if(!user) {
      return;
    }

    store.dispatch(saveUser(user));

    const { userId } = user;
    
    store.dispatch(check(userId));
  } catch(e) {
    console.log(e);
  }
}

sagaMiddleware.run(rootSaga);
loadUser();

ReactDOM.render(
  <Provider store={ store }>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>,
  document.getElementById('root')
);
reportWebVitals();