
import './App.css'; 
import { CohortsData } from './Components/Cohort';
import CohortDetails from './Components/CohortDetails';

function App() {
  return (
    <div className="App">
      <h1>Cohorts Details</h1>
      {CohortsData.map((cohort) => (
        <CohortDetails key={cohort.id} cohort={cohort} />
      ))}
    </div>
  );
}

export default App;
