import React from 'react';

const CourseDetails = ({ show }) => {
  if (!show) return null; // Using `if` for conditional rendering

  const courses = [
    { name: "Angular", date: "4/5/2021" },
    { name: "React", date: "6/3/20201" }
  ];

  return (
    <div>
      <h2>Course Details</h2>
      {courses.map((course, index) => (
        <div key={index}>
          <h3>{course.name}</h3>
          <p>{course.date}</p>
        </div>
      ))}
    </div>
  );
};

export default CourseDetails;
