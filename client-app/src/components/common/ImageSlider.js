import React from 'react';
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import styled from 'styled-components';
import ImageCard from './ImageCard';

const SliderBlock = styled.div`
    width: 500px;
`;

const ImageSlider = ({ Images }) => {
    return(
        <SliderBlock>
            <Carousel showArrows={ true }>
                {
                    Images.map((image, i) => {
                        return  <ImageCard 
                                    image={ image }
                                    i={ i }
                                />
                    })
                }
            </Carousel>
        </SliderBlock>
    );
};

export default ImageSlider;