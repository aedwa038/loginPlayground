# Login Playground App

[![Build Status](https://travis-ci.org/aedwa038/loginPlayground.svg?branch=master)](https://travis-ci.org/aedwa038/loginPlayground)


# curl commnads

## testing
curl -X GET \
  http://localhost:9080/playground/test/ \
  -H 'Postman-Token: 2ad2ee3d-2f8b-4fc8-b40c-57b50c5894c5' \
  -H 'cache-control: no-cache'
  
  
  ## register
  curl -X POST \
  http://localhost:9080/playground/register/ \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 40845cf0-387d-486e-a4d0-2b0ec0643d94' \
  -H 'cache-control: no-cache' \
  -d '{
	"username": "keem",
	"password":"keem",
	"email": "keem@keem.com"
}'

## login

curl -X POST \
  http://localhost:9080/playground/login/ \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: e3bdc9f7-9d7c-4cb9-93cf-149e367bd713' \
  -H 'cache-control: no-cache' \
  -d '{
	"username": "keem",
	"password":"keem"
}'

## List stuff

curl -X GET \
  http://localhost:9080/rest/stuff/1/ \
  -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJteXNlcnZlciIsImV4cCI6MTU0NzA5NTQzMywidXNlciI6IntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJrZWVtXCIsXCJlbWFpbFwiOlwia2VlbVwiLFwicmVnaXN0ZXJlZERhdGVcIjp7XCJkYXRlXCI6e1wieWVhclwiOjIwMTgsXCJtb250aFwiOjEyLFwiZGF5XCI6MzF9LFwidGltZVwiOntcImhvdXJcIjo0LFwibWludXRlXCI6NDMsXCJzZWNvbmRcIjo1MCxcIm5hbm9cIjozNDcwMDAwMDB9fSxcImxhc3RMb2dpbkRhdGVcIjp7XCJkYXRlXCI6e1wieWVhclwiOjIwMTgsXCJtb250aFwiOjEyLFwiZGF5XCI6MzF9LFwidGltZVwiOntcImhvdXJcIjo0LFwibWludXRlXCI6NDMsXCJzZWNvbmRcIjo1MCxcIm5hbm9cIjozNjMzMzkwMDB9fX0ifQ.VYUg-ULCcR0IcW4KC9Hmq-vRIwgKLxsUvImfL8Do__I' \
  -H 'Postman-Token: 66c3294d-37fb-48df-8ac7-53b9bde36f06' \
  -H 'cache-control: no-cache'
  
  ## Add stuff
  
  curl -X PUT \
  http://localhost:9080/rest/stuff/1/ \
  -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJteXNlcnZlciIsImV4cCI6MTU0NzA5NTQzMywidXNlciI6IntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJrZWVtXCIsXCJlbWFpbFwiOlwia2VlbVwiLFwicmVnaXN0ZXJlZERhdGVcIjp7XCJkYXRlXCI6e1wieWVhclwiOjIwMTgsXCJtb250aFwiOjEyLFwiZGF5XCI6MzF9LFwidGltZVwiOntcImhvdXJcIjo0LFwibWludXRlXCI6NDMsXCJzZWNvbmRcIjo1MCxcIm5hbm9cIjozNDcwMDAwMDB9fSxcImxhc3RMb2dpbkRhdGVcIjp7XCJkYXRlXCI6e1wieWVhclwiOjIwMTgsXCJtb250aFwiOjEyLFwiZGF5XCI6MzF9LFwidGltZVwiOntcImhvdXJcIjo0LFwibWludXRlXCI6NDMsXCJzZWNvbmRcIjo1MCxcIm5hbm9cIjozNjMzMzkwMDB9fX0ifQ.VYUg-ULCcR0IcW4KC9Hmq-vRIwgKLxsUvImfL8Do__I' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 07f622d2-8405-44e6-89e1-f09170d06e09' \
  -H 'cache-control: no-cache' \
  -d '{"data":"{}"}'

## g=Get stuff

curl -X GET \
  http://localhost:9080/rest/stuff/1/5/ \
  -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJteXNlcnZlciIsImV4cCI6MTU0NzA5NTQzMywidXNlciI6IntcImlkXCI6MSxcInVzZXJuYW1lXCI6XCJrZWVtXCIsXCJlbWFpbFwiOlwia2VlbVwiLFwicmVnaXN0ZXJlZERhdGVcIjp7XCJkYXRlXCI6e1wieWVhclwiOjIwMTgsXCJtb250aFwiOjEyLFwiZGF5XCI6MzF9LFwidGltZVwiOntcImhvdXJcIjo0LFwibWludXRlXCI6NDMsXCJzZWNvbmRcIjo1MCxcIm5hbm9cIjozNDcwMDAwMDB9fSxcImxhc3RMb2dpbkRhdGVcIjp7XCJkYXRlXCI6e1wieWVhclwiOjIwMTgsXCJtb250aFwiOjEyLFwiZGF5XCI6MzF9LFwidGltZVwiOntcImhvdXJcIjo0LFwibWludXRlXCI6NDMsXCJzZWNvbmRcIjo1MCxcIm5hbm9cIjozNjMzMzkwMDB9fX0ifQ.VYUg-ULCcR0IcW4KC9Hmq-vRIwgKLxsUvImfL8Do__I' \
  -H 'Postman-Token: 697a342d-9815-4608-aabc-dda3bb1eec06' \
  -H 'cache-control: no-cache'

