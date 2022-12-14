# tasveer
Tasveer is a gallery App

# Design
This app is designed in MVVM + Clean Architecture. 

# The app has following packages:
1) di - it contains hilt modules
2) data - it contains repositories
3) domain - it contains usecases 
4) ui - it contains fragments/viewmodel/adapters
5) common - it contains common files that are shared between classes i.e extensions/binding adapters

# Tools
1) Data Binding - for mapping data from viewmodel to xml.
2) Hilt-Android - for dependency injection.
3) ViewModel - mediator between ui and the data layer.
4) Coroutines - for asyn calls.
5) LiveData - for observable data holder.

