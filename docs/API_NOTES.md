## GET /productsList

**Method:** GET
**Full URL:** https://automationexercise.com/api/productsList
**Params:** None

**Status Code:** 200

**Response Structure:**
{
"responseCode": 200,
"products": [
{
"id": 1,
"name": "...",
"price": "...",
"brand": "...",
"category": { ... }
}
]
}

**Observations:**
- Top-level wrapper has "responseCode" + "products" array
- Each product has nested "category" object

## POST /searchProduct

**Method:** POST
**Full URL:** https://automationexercise.com/api/searchProduct

**Request Parameter:**
search_product=<keyword>

Example:
search_product=top
search_product=tshirt
search_product=jean

**Status Code:** 200

**Response Structure:**
{
"responseCode": 200,
"products": [
{
"id": 2,
"name": "...",
"price": "...",
"brand": "...",
"category": {
"usertype": {
"usertype": "..."
},
"category": "..."
}
}
]
}

**Observations:**
- Uses POST request (not GET) for searching products.
- Request body must include the mandatory parameter `search_product`.
- Response contains a top-level `responseCode` and a `products` array.
- Each matching product includes `id`, `name`, `price`, `brand`, and a nested `category` object.
- The `products` array may contain one or multiple matching products depending on the search keyword.
- If no matching products are found, the API may return an empty `products` array while still responding with HTTP 200.