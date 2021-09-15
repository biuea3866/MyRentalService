import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';
import Shortcut from './Shortcut';
import home_outline from '../../static/img/home-outline.svg';
import clipboard_outline from '../../static/img/clipboard-outline.svg';
import person_outline from '../../static/img/person-outline.svg';

const HeaderBox = styled.div`
    width: 100%;
    height: 130px;
    position: fixed;
    background-color: white;
    z-index: 2;
    box-shadow: 0 1px 2px 1px rgba(0, 0, 0, 0.1);
`;

const Title = styled.div`
    float: left;
    width: 30%;
    height: 130px;
    font-weight: bold;
    font-size: 40px;
    display: flex;
    justify-content: left;
    align-items: center;
    padding-left: 30px;
    a {
        color: ${palette.blue[0]};
    }
`;

const Nav = styled.div`
    float: left;
    width: 60%;
    height: 130px;
    display: flex;
    justify-content: flex-end;
    align-items: center;
`;

const HeaderTemplate = () => {
    return(
        <HeaderBox>
            <Title>
                <Link to="/">
                    Rental Platform
                </Link>
            </Title>
            <Nav>
                <Shortcut path="/"
                          src={ home_outline }
                />
                <Shortcut path="/posts"
                          src={ clipboard_outline }
                />
                <Shortcut path="/user/my-account"
                          src={ person_outline }
                />
            </Nav>
        </HeaderBox>
    );
};

export default HeaderTemplate;