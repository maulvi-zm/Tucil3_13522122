"use client";

import { InputForm } from "@/components/input-form";
import { useState } from "react";
import Result from "@/components/Result";

export default function Home() {
  const [start, setStart] = useState("");
  const [goal, setGoal] = useState("");
  const [algorithm, setAlgorithm] = useState("UCS");

  function handleSubmit(formData: {
    start: string;
    goal: string;
    algorithm: string;
  }) {
    setStart(formData.start);
    setGoal(formData.goal);
    setAlgorithm(formData.algorithm);
  }

  return (
    <main className='flex min-h-screen w-[100vw] flex-col items-center justify-between p-24'>
      <div className='z-10 w-full items-center justify-center font-mono text-sm flex flex-col gap-20 w'>
        <InputForm handleSubmit={handleSubmit} />
        <Result start={start} goal={goal} algorithm={algorithm} />
      </div>
    </main>
  );
}
