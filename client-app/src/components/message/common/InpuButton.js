import React from "react";
import styled from "styled-components";
import palette from "../../../lib/styles/palettes";

const SearchButtonArea = styled.div`
    width: 60px;
    float: left;
`;

const Button = styled.button`
    width: 90px;
    height: 40px;
    border-radius: 4px;
    background-color: ${ palette.blue[1] };
    color: #ffffff;
    outline: none;
    border: none;
    &: hover {
        width: 90px;
        height: 40px;
        border-radius: 4px;
        background-color: ${ palette.blue[2] };
        color: #ffffff;
        outline: none;
        border: none;
    }
`;

const SearchButton = ({ text, onClick }) =>{
    return(
        <SearchButtonArea>
            <Button onClick={ onClick }>
                { text }
            </Button>
        </SearchButtonArea>
    );
};

export default SearchButton;