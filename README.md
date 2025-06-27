Spring Boot Redis Cache

![Before Cache Redis](https://github.com/user-attachments/assets/42c6834d-70f3-497a-96f9-f3c064df462c)

<br><br>

![After Cache Redis](https://github.com/user-attachments/assets/7f23f21c-b72b-4b80-8edf-085ab872fa6d)

<br><br>

## API Endpoints

### Product
- `POST /product/create` → Create a new product
- `GET /product/{id}` → Get product by ID
- `GET /product` → Get all products
- `PUT /product/{id}` → Update product
- `DELETE /product/{id}` → Delete product
- `Get /product/sort/{field}` -> Find All Products By Sorting
- `Get /product/pagination/{offset}/{pageSize}` -> Find All Products With Pagination
- `Get /product/paginationAndSort/{offset}/{pageSize}/{field}` -> Find Products With Pagination And Sorting

  server:
  port: 9012
