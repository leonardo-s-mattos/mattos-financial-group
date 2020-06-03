db.createUser(
    {
      user: "admin",
      pwd: "merchant",
      roles: [
        {
          role: "readWrite",
          db: "merchant-database"
        }
      ]
    }
);