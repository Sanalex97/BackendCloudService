select cloudservice.myusers."auth-token"
from cloudservice.myusers
where lower(login) = lower(:login)
  and password = :password;