import React from 'react';
import { Route } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import MessagePage from './pages/MessagePage';
import MyPage from './pages/MyPage';
import PostDetailPage from './pages/PostDetailPage';
import PostPage from './pages/PostPage';
import RegisterPage from './pages/RegisterPage';
import WritePage from './pages/WritePage';
import RequestPage from './pages/RequestPage';

const App = () => {
    return(
        <>
            <Route component={ HomePage }
                   path="/"
                   exact
            />
            <Route component={ MyPage }
                   path="/user/my-account"
                   exact
            />
            <Route component={ RequestPage }
                   path="/user/rental-request"
                   exact
            />
            <Route component={ LoginPage }
                   path="/auth/login"
                   exact
            />
            <Route component={ RegisterPage }
                   path="/auth/register"
                   exact
            />
            <Route component={ PostPage }
                   path="/posts"
                   exact
            />
            <Route component={ WritePage }
                   path="/posts/write"
                   exact
            />
            <Route component={ PostDetailPage }
                   path="/posts/post/:id"
                   exact
            />
            <Route component={ MessagePage }
                   path="/messages"
                   exact
            />
            <Route component={ MessagePage }
                   path="/messages/:writer"
                   exact
            />
        </>
    );
};

export default App;