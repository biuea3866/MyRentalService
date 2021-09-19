import React from 'react';
import styled from 'styled-components';

const ImageBlock = styled.div`
    width: 100%;
`;

const Image = styled.img`
`;

const ImageCard = ({ image, i }) => {
    return(
        <ImageBlock>
            <Image src={ "data:image/png;base64," +  image.filePath }
                   alt="legend"
            />
        </ImageBlock>
    );
};

export default ImageCard;