# backend: description

- application microservices-base to manage: employees, addresses, projects and companies
	- each employee is associated an address
	- each project is assigned an employee and a projcet is destined to a company
- the backend application is implemented ***microservices base*** architecture
- each business microservice is implemented into ***clean architecture*** pattern

- framework used:
	- spring and spring boot

- business microservices (bs-ms) of the application:

	- ***k8s-clean-archi-bs-ms-address***
	- ***k8s-clean-archi-bs-ms-employee***
	- ***k8s-clean-archi-bs-ms-project***
	- ***k8s-clean-archi-bs-ms-company***

- utility microservices:
	- ***k8s-ms-config-service***: a config server to expose configurations to services
	- ***k8s-gateway-service***: a gateway between the back and front
	
- application microservices communicate each other (1); management of alternative scenarios and resilience (2)
	- (1) **spring cloud openfeign**
	- (2) spring cloud circuit breaker: **Resilience4J**

- ***kubernetes service siscovery*** dependecy is used in microservices to permit them to be discovered, load balanced and configured

- each microservice get its configuration properties from: 
	- a spring cloud configuration server: ***k8s-ms-config-service***
	- the config server pulls the needed configurations from a center of all configuration files [here](https://github.com/placidenduwayo1/config-files-center.git)

# application services containerization

- each microservice is containerized using **Docker Engine**, all application microservices are deployed using a template file: **docker compose**
    
here is the git of template that deploys all application microservices in docker images: [docker-compose](https://github.com/placidenduwayo1/K8s-AEPC-Docker-Deploy.git)

# deploying docker containers in K8s cluster
Now, all docker containers of microservices of the application are deployed into Kubernetes cluster. To get start with containers orchestration with Kubernetes, refer to doc [Kubernetes](https://kubernetes.io/fr/docs/home/)

each microservice container is deployed into ***k8s pod***, a ***k8s service*** is created to expose the pod outside, each microservice container is deployed into 3 replica pod. 

here is the git of deploying all microservices container into kubernetes cluster: [kubernetes cluster](https://github.com/placidenduwayo1/K8s-AEPC-Containers-Deploy.git)

## prerequisite:
- In local, you need have Minikube and Kubectl running.
- Minikube installation create a one node cluster to have master node and worker node in the same computer. You can create more than one node cluster by this command line: 
	- >```minikube --nodes nb-of-node --profile profile-flag start```
	- >```minikube --nodes 3 --profile nodes start```: the commande line create 3 nodes with profile nodes 
- Kubectl is a Kubernets CLI to make developer to communicate with the master node

more information about Minikube and Kubectl, documentations here: [Minikube](https://kubernetes.io/fr/docs/setup/learning-environment/minikube/) and [Kubectl](https://kubernetes.io/fr/docs/tasks/tools/install-kubectl/)

# architecture
the [figure](https://drive.google.com/file/d/1bedn0GuPzPgybFJBWTytlyV3dxl4WJiL/view?usp=drive_link) show the global and detailed architecture of project
