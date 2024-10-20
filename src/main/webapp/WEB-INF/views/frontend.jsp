<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rule Engine</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        div {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin: 20px auto;
            padding: 20px;
            max-width: 400px;
        }

        h2 {
            color: #555;
        }

        input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #218838;
        }

        #response {
            margin-top: 20px;
            font-weight: bold;
            color: #333;
        }
    </style>
</head>
<body>

<h1>Rule Engine</h1>

<div>
    <h2>Create Rule</h2>
    <input type="text" id="ruleInput" placeholder="Enter a rule" />
    <button onclick="createRule()">Create Rule</button>
</div>

<div>
    <h2>Combine Rules</h2>
    <input type="text" id="jsonInput" placeholder="Enter strings Seprated by coma" />
    <button onclick="combineRule()">Combine Rules</button>
</div>

<div id="response"></div>

<script>
    async function createRule() {
        const rule = document.getElementById("ruleInput").value;
        try {
            const response = await fetch('/create-rule', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({ rule })
            });

            const result = await response.text();
            console.log(result);
            document.getElementById("response").innerText = `Create Rule Response:` + result;
        } catch (error) {
            console.error('Error:', error);
        }
    }

   async function combineRule() {
       const s = document.getElementById("jsonInput").value; // Get the input value
       try {
           const response = await fetch('/combine-rule', {
               method: 'POST',
               headers: {
                   'Content-Type': 'application/x-www-form-urlencoded'
               },
               body: new URLSearchParams({ s }) // Send the string as a URL-encoded parameter
           });

           const result = await response.text(); // Expecting a text response
           console.log(result);
           document.getElementById("response").innerText = `Combine Rule Response: ` + result;
       } catch (error) {
           console.error('Error:', error);
       }
   }


</script>

</body>
</html>
