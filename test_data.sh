for role in doctor user pharmacist; do
  curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d "{
    \"username\": \"$role\",
    \"email\": \"$role@example.com\",
    \"password\": \"${role}123\",
    \"role\": \"$role\",
    \"firstName\": \"Test\",
    \"lastName\": \"User\",
    \"phone\": \"000000000\"
  }"
  echo -e "\nFinished $role"
done