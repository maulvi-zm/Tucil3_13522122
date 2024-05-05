# Tugas Kecil 3 Strategi Algoritma IF2211

## Solve Word Ladder with Uniform Cost Search, Greedy Best First Search, A\*

## Table of Contents

- [Table of Contents](#table-of-contents)
- [General Information](#general-information)
- [Contributor](#contributor)
- [Dependencies](#dependencies)
- [How to Use](#how-to-use)

## General Information

The GitHub repository dedicated to solving the Word Ladder problem with Uniform Cost Search (UCS), Greedy Best First Search, and A\* Algorithm offers a comprehensive solution set for tackling this intriguing word transformation challenge. The repository houses meticulously crafted implementations of each algorithm, finely tuned to efficiently explore the search space and derive optimal or near-optimal solutions. Users can clone the repository locally, facilitating seamless integration into their projects. The repository not only provides the algorithms themselves but also includes functionalities for processing input data, such as start and target words, as well as the dictionary containing valid words.

## Contributor

| Name                    | NIM      |
| ----------------------- | -------- |
| Maulvi Ziadinda Maulana | 13522122 |

## Dependencies

Node package manager, Java 17, Docker

## How to Use

If you have docker, use this procedure

1. Clone this repository
   ```
   git clone https://github.com/maulvi-zm/Tucil3_13522122
   ```
2. Navigate to source folder
   ```
   cd Tucil3_13522122/src
   ```
3. Compose with docker
   ```
   docker compose up
   ```

If you don't have docker, use this procedure

1. Clone this repository
   ```
   git clone https://github.com/maulvi-zm/Tucil3_13522122
   ```
2. Navigate to frontend folder
   ```
   cd Tucil3_13522122/frontend
   ```
3. Install dependencies
   ```
   npm install
   ```
4. Run the frontend
   ```
   npm run dev
   ```
5. Open a new terminal, navigate to backend folder
   ```
   cd Tucil3_13522122/backend
   ```
6. Compile the program into a jar file
   ```
   ./mvnw clean package
   ```
7. Run the backend
   ```
   java -jar /target/backend-0.0.1-SNAPSHOT.jar
   ```
