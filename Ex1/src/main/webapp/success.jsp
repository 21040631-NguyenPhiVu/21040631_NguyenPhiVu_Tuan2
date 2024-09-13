<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login successful</title>
    <style>
        body {
            background-color: #f0f8ff;
            font-family: 'Arial', sans-serif;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #4CAF50;
            font-size: 2.5em;
        }
        .message {
            margin: 20px auto;
            padding: 20px;
            background-color: #dff0d8;
            color: #3c763d;
            border: 1px solid #d6e9c6;
            width: 60%;
            border-radius: 10px;
        }
        button {
            display: inline-block;
            padding: 10px 20px;
            margin: 20px 10px;
            font-size: 1em;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <h1>Success!</h1>

    <div class="message">
        <h2>Hello, ${username}!</h2>
        <p>We are excited to have you back. Feel free to explore our features or return to the homepage.</p>
    </div>
    
    <form action="logout" method="post">
        <button type="submit">Back to Login Page</button>
    </form>
    
</body>
</html>
