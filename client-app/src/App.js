import React from 'react';
import { Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import MyPage from './pages/MyPage';

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
        </>
    );
};

export default App;