# impact_ybky

- [x] API
- /api/rooms/1
 ```json
  {
      "roomId": 1,
      "name": "my_taxi",
      "capacity": 1,
      "type": "CONFERENCE"
  }
 ```

- [x] available of room
- /api/rooms/1/available

```json
 [
  {
    "book_id": 1,
    "start": "2023-06-19T09:00:00",
    "end": "2023-06-19T18:00:00",
    "active": null,
    "room": {
      "roomId": 1,
      "name": "my_taxi",
      "capacity": 15,
      "type": "CONFERENCE"
    }
  }
]
```

-[x] book room
- /api/rooms/1/book
- body
 ```json5
{
	"start": "2023-06-04T12:43:00",
	"end": "2023-06-04T13:00:00"
}
```

- response
```text
    true  joy bo'lsa 
    aks holda 
    "error": "bu xona bu vaqtda band"
```

- [x] pagination
```text
    params:
     page
     page_size
```

- result
```json
{
  "page": 0,
  "count": 4,
  "page_size": 4,
  "results": [
    {
      "roomId": 1,
      "name": "my_taxi",
      "capacity": 1,
      "type": "CONFERENCE"
    },
    {
      "roomId": 2,
      "name": "workly",
      "capacity": 5,
      "type": "TEAM"
    },
    {
      "roomId": 3,
      "name": "express_24",
      "capacity": 15,
      "type": "FOCUS"
    },
    {
      "roomId": 4,
      "name": "super_dipatch",
      "capacity": 19,
      "type": "CONFERENCE"
    }
  ]
}
```
```text
  resources/sql/room.sql file datalar uchun
  
```

