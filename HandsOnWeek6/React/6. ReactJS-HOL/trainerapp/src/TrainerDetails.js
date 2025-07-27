import React from "react";
import { useParams } from "react-router-dom";
import trainersMock from "./TrainersMock";

const TrainerDetails = () => {
  const { id } = useParams();
  const trainer = trainersMock.find((t) => t.TrainerId === id);

  if (!trainer) {
    return <p>Trainer not found.</p>;
  }

  return (
    <div>
      <h2>{trainer.Name}</h2>
      <p><strong>Trainer ID:</strong> {trainer.TrainerId}</p>
      <p><strong>Email:</strong> {trainer.Email}</p>
      <p><strong>Phone:</strong> {trainer.Phone}</p>
      <p><strong>Technology:</strong> {trainer.Technology}</p>
      <p><strong>Skills:</strong> {trainer.Skills.join(", ")}</p>
    </div>
  );
};

export default TrainerDetails;
