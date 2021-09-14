import client from './client';

export const write = ({
    userId,
    type,
    category,
    title,
    content,
    rentalPrice,
    date,
    writer,
    images
}) => {
    if(type === '빌려주세요') {
        const formData = new FormData();

        formData.append('userId', userId);
        formData.append('type', type);
        formData.append('title', title);
        formData.append('content', content);
        formData.append('writer', writer);
        
        client.post('/post-service/write', formData);
    } else {
        const formData = new FormData();

        formData.append('userId', userId);
        formData.append('category', category);
        formData.append('images', images);
        formData.append('type', type);
        formData.append('title', title);
        formData.append('content', content);
        formData.append('rentalPrice', Number(rentalPrice));
        formData.append('date', date);
        formData.append('writer', writer);
        images.forEach((image) => formData.append("images", image));
        
        client.post('/post-service/write', formData);
    }
}
export const readAllPosts = () => client.get('/post-service');

export const readPostsByStatus = status => client.get(`/post-service/posts/status/${status}`)

export const readPostById = id => client.get(`/post-service/post/${id}`);

export const readPostsByUserId = userId => client.get(`/post-service/${userId}/posts`);

export const readPostsByCategory = category => client.get(`/post-service/category/${category}`);

export const readPostsByKeyword = keyword => client.get(`/post-service/keyword/${keyword}`);

export const deletePost = id => client.post(`/post-service/${id}/delete`);

export const writeComment = ({ id, comment }) => client.post(`/post-service/${id}/comments`, comment);

export const deleteComment = id => client.delete(`/post-service/${id}/comments`); 