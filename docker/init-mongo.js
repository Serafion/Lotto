db.createUser(
    {
        user: "userbase",
        pwd: "qwertyasdfg",
        roles: [
            {
                role: "readWrite",
                db: "tickets"
            }
        ]
    }
)
