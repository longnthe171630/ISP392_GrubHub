<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add new products from file</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa; /* Light gray background */
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .container {
                max-width: 400px;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }

            h1 {
                font-size: 24px;
                margin-bottom: 20px;
            }

            form {
                margin-top: 20px;
            }

            label {
                font-weight: bold;
                margin-bottom: 8px;
                display: block;
            }

            input[type=file] {
                margin-bottom: 16px;
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            button {
                background-color: #28a745; /* Green color */
                color: #fff;
                border: none;
                padding: 10px 20px;
                cursor: pointer;
                border-radius: 4px;
            }

            button:hover {
                background-color: #218838; /* Darker shade of green */
            }

        </style>
    </head>
    <body>
        <div class="container">
            <h1>Add new products from file: </h1>
            <form action="uploadproductfromfile" method="post" enctype="multipart/form-data">
                <label for="file">Choose file (CSV or XML):</label>
                <input type="file" id="file" name="file" accept=".csv, .xml" required>
                <button type="submit">Upload</button>
            </form>
        </div>
    </body>
</html>
