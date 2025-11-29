1. Запустить Docker Desktop под администратором
2. Запустить IDE под администратором
3. # minikube delete
4. minikube start
5. В отдельном окне запустить
   1. minikube tunnel
6. cd .\crud-user-db
7. helm install bitnami oci://registry-1.docker.io/bitnamicharts/postgresql -f ./Helm/postgres-values.yaml <- Установить Postgres
8.  kubectl get pods
9. minikube image ls
10. minikube image rm mantogin/mantogin-crud-user-db:1.0.0 <- Выполнить команду если в выводе предыдущей комадны в списке образов был образ mantogin/mantogin-crud-user-db:1.0.0
11. docker build --platform linux/amd64 -t mantogin/mantogin-crud-user-db:1.0.0 . <- сборка для заливки на dockerhub
12. docker build --platform linux/amd64 -t mantogin/mantogin-crud-user-db:1.0.0 . <- сборка для заливки на dockerhub
13. docker image push mantogin/mantogin-crud-user-db:1.0.0 <- размещение образа на dockerhub
14. https://hub.docker.com/repository/docker/mantogin/mantogin-crud-user-db/general <- Проверить, что докер залился на докерхаб
15. kubectl apply -f ./Kuber
16. kubectl get pods <- В выводе должен быть pod db-job-*
17. kubectl logs db-job-* <- В выводе должны быть строки CREATE DATABASE, CREATE TABLE
18. kubectl exec -it bitnami-postgresql-0 -- /bin/bash <- подключиться к поду командной строкой
19. psql -h localhost -p 5432 -U postgres <- подключиться к psql - пароль из файла [postgres-values.yaml](crud-user-db/Helm/postgres-values.yaml)
20. \l <- В выводе должна быть база mcua_db
21. \q
22. exit
23. cd ..\crud-user-app\
24. mvn clean install -U <- собрать приложение
25. minikube image ls
26. minikube image rm mantogin/mantogin-crud-user-app:1.0.0 <- Выполнить команду если в выводе предыдущей комадны в списке образов был образ mantogin/mantogin-crud-user-app:1.0.0
27. docker build --platform linux/amd64 -t mantogin/mantogin-crud-user-app:1.0.0 . <- сборка для заливки на dockerhub
28. docker image push mantogin/mantogin-crud-user-app:1.0.0 <- размещение образа на dockerhub
29. https://hub.docker.com/repository/docker/mantogin/mantogin-crud-user-app/general <- Проверить, что докер залился на докерхаб
30. kubectl apply -f ./Kuber
31. kubectl get pods <- В выводе должен быть pod mantogin-crud-user-app-*
32. cd .. 
33. 127.0.0.1 arch.homework <- Прописать под правами администратора в файле etc/host строку
34. newman run mantogin-crud-user-app-postman-collection.postman_collection.json