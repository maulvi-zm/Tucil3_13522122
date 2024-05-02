import React from "react";

function PathBlock({ path }: { path: string[] }) {
  if (!path) {
    return <div>Calculating path...</div>;
  }

  const end = path[path.length - 1];

  return (
    <>
      {path.map((node, index) => {
        return (
          <div key={index} className='flex items-center relative'>
            <div className='text-2xl w-24 h-24 flex justify-center items-center absolute -translate-x-[100%] top-0'>
              {index + 1}
            </div>
            <div className='flex mb-6 border-2 divide-x-2'>
              {node.split("").map((char, index) => {
                return (
                  <div
                    key={index}
                    className={
                      `text-4xl w-24 h-24 flex justify-center items-center` +
                      (char === end[index] ? " bg-green-500" : "")
                    }
                  >
                    {char}
                  </div>
                );
              })}
            </div>
          </div>
        );
      })}
    </>
  );
}

export default PathBlock;
