import client from './client';

export const write = ({
    userId,
    postType,
    category,
    title,
    content,
    rentalPrice,
    date,
    writer,
    images
}) => {
    if(postType === '빌려주세요') {
        const formData = new FormData();

        formData.append('userId', userId);
        formData.append('postType', postType);
        formData.append('title', title);
        formData.append('content', content);
        formData.append('writer', writer);
        
        client.post('/post-service/write', formData);
    } else {
        const formData = new FormData();

        formData.append('userId', userId);
        formData.append('category', category);
        formData.append('images', images);
        formData.append('postType', postType);
        formData.append('title', title);
        formData.append('content', content);
        formData.append('rentalPrice', Number(rentalPrice));
        formData.append('date', date);
        formData.append('writer', writer);
        images.forEach((image) => formData.append("images", image));
        
        client.post('/post-service/write', formData);
    }
};

export const readAllPosts = () => client.get('/post-service/');

export const readPostsByStatus = status => client.get(`/post-service/posts/status/${status}`)

export const readPostById = id => client.get(`/post-service/post/${id}`);

export const readPostsByUserId = userId => client.get(`/post-service/${userId}/posts`);

export const readPostsByCategory = category => client.get(`/post-service/category/${category}`);

export const readPostsByKeyword = keyword => client.get(`/post-service/keyword/${keyword}`);

export const deletePost = id => client.post(`/post-service/${id}/delete`);

export const writeComment = ({ 
    postId,
    writer, 
    comment, 
}) => client.post(`/post-service/comments`, {
    postId,
    writer, 
    comment
});

export const deleteComment = id => client.delete(`/post-service/${id}/comments`); 

export const createRental = ({
    postId,
    owner,
    borrower,
    price,
    startDate,
    endDate
}) => client.post('/post-service/rental', {
    postId,
    owner,
    borrower,
    price,
    startDate,
    endDate
});

export const rollbackStatus = postId => client.post(`/post-service/rollback/${postId}`);