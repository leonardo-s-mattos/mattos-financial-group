db.createUser(
    {
      user: "admin",
      pwd: "bank",
      roles: [
        {
          role: "readWrite",
          db: "bank-database"
        }
      ]
    }
);