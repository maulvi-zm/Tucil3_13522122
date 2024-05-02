import React, { useEffect } from "react";
import PathBlock from "./PathBlock";

interface algorithmResult {
  time: number;
  path: string[];
  total_nodes: number;
  error_code: number;
  error_message: string;
  memory: number;
}

function Result({
  start,
  goal,
  algorithm,
}: {
  start: string;
  goal: string;
  algorithm: string;
}) {
  const [result, setResult] = React.useState<algorithmResult>({
    time: 0,
    path: [],
    total_nodes: 0,
    error_code: -1,
    error_message: "",
    memory: 0,
  });

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await fetch(
          "http://localhost:8080" +
            "/solve?" +
            `start=${start}&goal=${goal}&algorithm=${algorithm}`
        );
        const data = (await response.json()) as algorithmResult;
        setResult(data);
      } catch (e) {
        console.error(e);
      }
    }
    if (start && goal && algorithm) {
      fetchData();
    }
  }, [start, goal, algorithm]);

  if (!start || !goal || !algorithm) {
    return <div>Enter a start, goal, and algorithm</div>;
  }

  return (
    <div className='w-2/3 relative'>
      {result.error_code !== 0 ? (
        <p className='w-full text-center'>Error: {result.error_message}</p>
      ) : (
        <>
          <div className=' flex flex-col justify-between text-xl text-bold gap-8 border-2 absolute -right-16 p-4 bg-white'>
            <p>Time: {result.time} ms</p>
            <p>Total Nodes: {result.total_nodes}</p>
            <p>Memory: {result.memory} kB</p>
          </div>
          <div className='flex flex-col items-center'>
            <PathBlock path={result.path} />
          </div>
        </>
      )}
    </div>
  );
}

export default Result;
