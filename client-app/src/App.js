import React from 'react';
import { Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import MyPage from './pages/MyPage';
import PostDetailPage from './pages/PostDetailPage';
import PostPage from './pages/PostPage';
import RegisterPage from './pages/RegisterPage';
import WritePage from './pages/WritePage';

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
                path="/user/my-account"
                exact
            />
            <Route 
                component={ LoginPage }
                path="/auth/login"
                exact
            />
            <Route 
                component={ RegisterPage }
                path="/auth/register"
                exact
            />
            <Route
                component={ PostPage }
                path="/posts"
                exact
            />
            <Route 
                component={ WritePage }
                path="/posts/write"
                exact
            />
            <Route 
                component={ PostDetailPage }
                path="/posts/post/:postId"
            />
        </>
    );
};

export default App;