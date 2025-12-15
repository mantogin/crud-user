1. Запустить Docker Desktop под администратором
2. Запустить IDE под администратором
3. # minikube delete
4. minikube start
5. В отдельном окне запустить
    1. minikube tunnel
6. cd .\crud-user-app\
7. mvn clean install -U <- собрать приложение
8. minikube image ls
9. # minikube image rm mantogin/mantogin-crud-user-app:2.0.0 <- Выполнить команду если в выводе предыдущей комадны в списке образов был образ mantogin/mantogin-crud-user-app:1.0.0
10. docker build --platform linux/amd64 -t mantogin/mantogin-crud-user-app:2.0.0 . <- сборка для заливки на dockerhub
11. docker image push mantogin/mantogin-crud-user-app:2.0.0 <- размещение образа на dockerhub
12. https://hub.docker.com/repository/docker/mantogin/mantogin-crud-user-app/general <- Проверить, что докер залился на докерхаб
13. kubectl apply -f ./Kuber
14. kubectl get pods <- В выводе должен быть pod mantogin-crud-user-app-*
15. # minikube dashboard <- Отобразить Дашборд Кубера
16. helm repo add prometheus-community https://prometheus-community.github.io/helm-charts <- Добавить репозиторий
17. helm repo update
18. helm install stack prometheus-community/kube-prometheus-stack -f .\Helm\prometheus.yaml <- Установить Прометей с Графаной
19.  kubectl --namespace default get secrets stack-grafana -o jsonpath="{.data.admin-password}" <- Получить пароль к Графане в base64
20. Декодировать строку пароля полученную на предыдущем шаге
21. kubectl --namespace default get pod -l "app.kubernetes.io/name=grafana,app.kubernetes.io/instance=stack" -oname <- Получить имя Pod'а Графаны
22. kubectl --namespace default port-forward $POD_NAME 3000 <- Вместо $POD_NAME подставить имя Pod'а полученного на предыдущем шаге и запустить в отдельном окне
23. http://localhost:3000/ <- Запустить Графану, логин: admin, пароль: полученный на шаге 20 (декодированная строка пароля)
24. kubectl --namespace default port-forward pod/bitnami-postgresql-0 5432 <- Проброс порта до Пода Postgres
25. kubectl --namespace default port-forward pod/prometheus-stack-kube-prometheus-stac-prometheus-0 9090 <- Проброс порта до Пода Прометея
26. 