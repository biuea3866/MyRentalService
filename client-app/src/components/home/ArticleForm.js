import React from 'react';
import styled from 'styled-components';
import IconBlockTemplate from './common/IconBlockTemplate';
import book_outline from '../../static/img/book-outline.svg';
import laptop_outline from '../../static/img/laptop-outline.svg';
import bicycle_outline from '../../static/img/bicycle-outline.svg';
import barbell_outline from '../../static/img/barbell-outline.svg';

const ArticleFormBlock = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;

const ArticleTable = styled.table`
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;

const TableLine = styled.tr``;

const ArticleForm = () => {
    return(
        <ArticleFormBlock>
            <ArticleTable>
                <TableLine>
                    <IconBlockTemplate to="#"
                                       src={ book_outline }
                                       text="책"
                    />
                    <IconBlockTemplate to="#"
                                       src={ laptop_outline }
                                       text="가전제품"
                    />
                    <IconBlockTemplate to="#"
                                       src={ barbell_outline }
                                       text="운동기구"
                    />
                    <IconBlockTemplate to="#"
                                       src={ bicycle_outline }
                                       text="자전거"
                    />
                </TableLine>
            </ArticleTable>
        </ArticleFormBlock>
    );
};

export default ArticleForm;