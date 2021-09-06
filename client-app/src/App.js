import React from 'react';
import { Route } from 'react-router-dom';
import HomePage from './pages/HomePage';

const App = () => {
    return(
        <>
            <Route 
                component={ HomePage }
                path="/"
                exact
            />
        </>
    );
};

export default App;