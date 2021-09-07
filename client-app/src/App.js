import React from 'react';
import { Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import MyPage from './pages/MyPage';
import RegisterPage from './pages/RegisterPage';

const App = () => {
    return(
        <>
            <Route 
                component={ HomePage }
                path="/"
                exact
            />
            <Route 
                component={ MyPage }
                path="/user/myPage"
                exact
            />
            <Route 
                component={ LoginPage }
                path="/auth/LoginPage"
                exact
            />
            <Route 
                component={ RegisterPage }
                path="/auth/RegisterPage"
                exact
            />
        </>
    );
};

export default App;