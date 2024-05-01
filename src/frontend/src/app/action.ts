"use server";

export async function getResults(formData: FormData) {
  const start = formData.get("start") as string;
  const goal = formData.get("goal") as string;
  const algorithm = formData.get("algorithm") as string;

  return fetch(
    process.env.BACKEND_URL +
      "/solve?" +
      `start=${start}&goal=${goal}&algorithm=${algorithm}`,
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    }
  ).then((res) => res.json());
}
