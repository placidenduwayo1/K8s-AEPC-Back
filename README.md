# backend: description

- application microservices-base to manage: employees, addresses, projects and companies
	- each employee is associated an address.
	- each project is assigned an employee and a projcet is destined to a company.
- the backend application is implemented ***microservices base*** architecture.
- each business microservice is implemented into ***clean architecture*** pattern.

- framework used:
	- spring, spring boot and spring cloud.

- business microservices (bs-ms) of the application:

	- ***k8s-aepc-clean-archi-bs-ms-address***
	- ***k8s-aepc-clean-archi-bs-ms-employee***
	- ***k8s-aepc-clean-archi-bs-ms-project***
	- ***k8s-aepc-clean-archi-bs-ms-company***

- utility microservices:
	- ***k8s-aepc-ms-config-service***: a config server to expose configurations to other services.
	- ***k8s-aepc-gateway-service***: a gateway between the back and front.

- each microservice gets its configuration properties from: 
	- a spring cloud configuration server: ***k8s-aepc-ms-config-service***.
	- the config server pulls the needed configurations from a git center of all configuration files [git](https://github.com/placidenduwayo1/config-files-center.git).
	
- microservices communicate each other using **(1)**; alternative scenarios and resilience are manager using **(2)** and microservices are discovered each other, loadbalanced and configured using **(3)**:
	- (1) **spring cloud openfeign**.
	- (2) spring cloud circuit breaker: **Resilience4J**.
	- (3) **spring cloud kubernetes**.

# microservices containerization

- each microservice is containerized using **Docker Engine**.
- all microservices are deployed using a **docker compose** template: [git](https://github.com/placidenduwayo1/K8s-AEPC-Docker-Deploy.git).

# containers orchestration in K8s cluster
- all docker containers of microservices of the application are deployed and orchestrated into k8s cluster.
	- to get start with containers orchestration with k8s, refer to doc [here](https://kubernetes.io/fr/docs/home/).

- each microservice container is deployed into **3 replica of k8s pods**.
- a ***k8s service*** is created for each deployment to expose to outside and load balance the pods of the deployment.

here is the git of deploying all microservices container into kubernetes cluster: [git](https://github.com/placidenduwayo1/K8s-AEPC-Containers-Deploy.git).

## prerequisite:
- to orchestrate containers with Kubernetes in local, runnig **Minikube** and **Kubectl** is mandatory.
- Minikube installation create a one node cluster to have Master and Worker Nodes on the same computer. 
- you can create more than one node cluster as follows: 
	- >```minikube --nodes nb-of-node --profile profile-flag start```.
	- >```minikube --nodes 3 --profile node start```: 
		- the commande line create a cluster with 3 nodes.
		- one of the three node is a Master Node et the 2 others are Worker Nodes.
- Kubectl is a Kubernetes CLI to make developer to communicate with the Master Node.

here is the documentations about those two tools: [Minikube-doc](https://kubernetes.io/fr/docs/setup/learning-environment/minikube/) and [Kubectl-doc](https://kubernetes.io/fr/docs/tasks/tools/install-kubectl/).

# architecture
the gloabal and detailed architecture of project can be viewed:
![k8s-containers-orchestration](https://github.com/placidenduwayo1/K8s-AEPC-Back/assets/124048212/993d0c70-9dd1-462c-b1c2-7b7a8c411148)
