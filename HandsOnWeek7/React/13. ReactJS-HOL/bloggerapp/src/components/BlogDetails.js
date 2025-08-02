import React from 'react';

const BlogDetails = ({ blogs }) => {
  // Using IIFE for conditional rendering
  return (
    <div>
      <h2>Blog Details</h2>
      {(() => {
        if (!blogs || blogs.length === 0) {
          return <p>No blogs found.</p>;
        }
        return blogs.map((blog, index) => (
          <div key={index}>
            <h3>{blog.title}</h3>
            <strong>{blog.author}</strong>
            <p>{blog.content}</p>
          </div>
        ));
      })()}
    </div>
  );
};

export default BlogDetails;
