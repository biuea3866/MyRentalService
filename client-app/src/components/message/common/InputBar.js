import React from 'react';
import styled from 'styled-components';
import Input from '../../common/Input';

const SearchBarArea = styled.div`
    float: left;
    width: inherit;
`;

const SearchBar = ({ placeholder }) => {
    return(
        <SearchBarArea>
            <Input name="searchBar"
                   type="text"
                   placeholder={ placeholder }
            />
        </SearchBarArea>
    );
};

export default SearchBar;